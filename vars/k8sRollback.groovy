def call(project) {
    sh """helm rollback \
        ${project} 0 \
        --namespace ${project}"""
    error "Failed production tests"
}
