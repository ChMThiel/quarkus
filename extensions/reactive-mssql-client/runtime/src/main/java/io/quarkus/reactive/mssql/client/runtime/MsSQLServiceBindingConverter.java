package io.quarkus.reactive.mssql.client.runtime;

import java.util.List;
import java.util.Optional;

import io.quarkus.kubernetes.service.binding.runtime.ReactiveDatasourceServiceBindingConfigSourceFactory;
import io.quarkus.kubernetes.service.binding.runtime.ServiceBinding;
import io.quarkus.kubernetes.service.binding.runtime.ServiceBindingConfigSource;
import io.quarkus.kubernetes.service.binding.runtime.ServiceBindingConverter;

public class MsSQLServiceBindingConverter implements ServiceBindingConverter {

    @Override
    public Optional<ServiceBindingConfigSource> convert(List<ServiceBinding> serviceBindings) {
        return ServiceBinding.singleMatchingByType("sqlserver", serviceBindings)
                .map(new ReactiveDatasourceServiceBindingConfigSourceFactory("sqlserver"));
    }
}
