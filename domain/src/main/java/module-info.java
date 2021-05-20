module clean.arhitecture.domain.main {
    requires jakarta.validation;
    requires static lombok;
    exports com.filos.exception;
    exports com.filos.port;
    exports com.filos.entity;
    exports com.filos.mapper;
    exports com.filos.requests;
}