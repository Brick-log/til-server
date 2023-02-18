rootProject.name = "til-server"

includeProject(":api", "src/api")
includeProject(":crawler", "src/crawler")

includeProject(":protocol", "libs/protocol")

fun includeProject(name: String, projectPath: String) {
    include(name)
    project(name).setProjectDir(file(projectPath))
}
