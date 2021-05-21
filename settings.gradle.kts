rootProject.name = "clean-arhitecture"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
enableFeaturePreview("VERSION_CATALOGS")
include("domain")
include("usecase")
include("adapter")
include("encoder")
include("adapter:encoder")
findProject(":adapter:encoder")?.name = "encoder"
include("adapter:repository")
findProject(":adapter:repository")?.name = "repository"
include("adapter:repository:in_memory_simple")
findProject(":adapter:repository:in_memory_simple")?.name = "in_memory_simple"
include("adapter:repository:hazelcast")
findProject(":adapter:repository:hazelcast")?.name = "hazelcast"
include("adapter:id-generator")
findProject(":adapter:id-generator")?.name = "id-generator"
include("adapter:id-generator:uuid")
findProject(":adapter:id-generator:uuid")?.name = "uuid"
include("adapter:encoder:sha-encoder")
findProject(":adapter:encoder:sha-encoder")?.name = "sha-encoder"
include("config")
include("application")
include("application:manual-app")
findProject(":application:manual-app")?.name = "manual-app"
