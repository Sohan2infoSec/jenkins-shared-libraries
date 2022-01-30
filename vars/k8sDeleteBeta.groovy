def call(project) {
    chartName = "${project}-${env.BUILD_NUMBER}-${env.BRANCH_NAME}"
    sh """helm uninstall ${chartName.toLowerCase()}
          --namespace ${project}-build"""
    sh """kubectl delete pvc \
            -l release=${chartName.toLowerCase()} \
            --namespace ${project}-build"""
}
