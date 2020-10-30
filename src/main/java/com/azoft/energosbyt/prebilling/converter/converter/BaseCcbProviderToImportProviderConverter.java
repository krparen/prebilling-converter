package com.azoft.energosbyt.prebilling.converter.converter;

import com.azoft.energosbyt.prebilling.converter.dto.input.BaseCcbProvider;
import com.azoft.energosbyt.prebilling.converter.dto.output.ImportProvider;
import com.azoft.energosbyt.prebilling.converter.dto.output.Provider;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BaseCcbProviderToImportProviderConverter
        extends AbstractConverter<BaseCcbProvider, ImportProvider> {

    @Override
    public ImportProvider convert(BaseCcbProvider input) {

        ImportProvider output = new ImportProvider();

        String systemCode = getInformSystemCode(input.getSystem_id());
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

        output.setProvider(providers);
        return output;
    }

}
