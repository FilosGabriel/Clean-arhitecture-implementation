module clean.arhitecture.config.main {
    requires clean.arhitecture.domain.main;
    requires clean.arhitecture.adapter.encoder.sha.encoder.main;
    requires clean.arhitecture.adapter.id.generator.uuid.main;
    requires clean.arhitecture.usecase.main;
    requires clean.arhitecture.adapter.repository.in.memory.simple.main;
    requires clean.arhitecture.adapter.repository.hazelcast.main;
    exports com.filos.configs;
}