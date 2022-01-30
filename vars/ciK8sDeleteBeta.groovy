def call(project) {
    chartName = ciChartNameRead(project)
    sh """helm delete ${chartName.toLowerCase()}"""
}
