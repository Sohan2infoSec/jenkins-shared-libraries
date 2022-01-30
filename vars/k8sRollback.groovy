def call(project) {
    sh """helm rollback \
        ${project} 0 \
        --namespace ${project}"""
    sendTelegram(env.JOB_BASE_NAME + ' error deploy.')
    error "Failed production tests"
}
