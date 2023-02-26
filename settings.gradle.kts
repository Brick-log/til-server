rootProject.name = "til-server"

includeProject(":api", "src/api")
includeProject(":crawler", "src/crawler")

includeProject(":protocol", "libs/protocol")
includeProject(":storage", "libs/storage")

fun includeProject(name: String, projectPath: String) {
    include(name)
    project(name).projectDir = file(projectPath)
}
