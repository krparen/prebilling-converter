package com.azoft.energosbyt.prebilling.converter.converter;

import com.azoft.energosbyt.prebilling.converter.dto.input.BaseCcbProvider;
import com.azoft.energosbyt.prebilling.converter.dto.output.ImportProvider;
import com.azoft.energosbyt.prebilling.converter.dto.output.Provider;
import com.azoft.energosbyt.prebilling.converter.service.ReferenceQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BaseCcbProviderToImportProviderConverter
        implements Converter<BaseCcbProvider, ImportProvider> {

    @Autowired
    private ReferenceQueryService referenceQueryService;

    @Override
    public ImportProvider convert(BaseCcbProvider input) {

        ImportProvider output = new ImportProvider();

        String systemCode = referenceQueryService.getInformSystemCode(input.getSystem_id());
        List<Provider> providers = input.getProvider().stream()
                .map(inputProvider -> {
                    Provider provider = new Provider();
                    provider.setInform_system(systemCode);
                    provider.setExt_id(inputProvider.getExt_id());
                    provider.setName(inputProvider.getName());
                    provider.setCode(inputProvider.getCode());
                    return provider;
                })
                .collect(Collectors.toList());

        output.setProviders(providers);
        return output;
    }

}
