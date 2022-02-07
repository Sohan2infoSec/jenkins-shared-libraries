 def call(image, sudo = true, prefix = "") {
    tagBeta = "${currentBuild.displayName}-${env.BRANCH_NAME}"
    if (sudo) {
        prefix = "sudo ${prefix}"
    }
    sh """${prefix} docker pull \
        ${image}:${tagBeta}"""
    sh """${prefix} docker image tag \
        ${image}:${tagBeta} \
        ${image}:${currentBuild.displayName}"""
    sh """${prefix} docker image tag \
        ${image}:${tagBeta} \
        ${image}:latest"""
    withCredentials([usernamePassword(
        credentialsId: "docker",
        usernameVariable: "USER",
        passwordVariable: "PASS"
    )]) {
        sh """${prefix} docker login \
        -u $USER -p $PASS"""
    }
    sh """${prefix} docker image push \
        ${image}:${currentBuild.displayName}"""
    sh """${prefix} docker image push \
        ${image}:latest"""
 }
