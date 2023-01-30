rootProject.name = "til-server"

includeProject(":api", "src/api")
includeProject(":crawler", "src/crawler")

fun includeProject(name: String, projectPath: String) {
    include(name)
    project(name).setProjectDir(file(projectPath))
}
