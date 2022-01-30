def call(project) {
    chartName = ciChartNameRead(project)
    sh """helm uninstall ${chartName.toLowerCase()} \
          --namespace ${project}-build"""
    sh """kubectl delete pvc \
            -l release=${chartName.toLowerCase()} \
            --namespace ${project}-build"""
}
