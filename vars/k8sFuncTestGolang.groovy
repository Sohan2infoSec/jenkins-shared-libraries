def call(project, addr) {
    sh "go get -d -v -t"
    sh """ADDRESS=${addr} \
        go test ./... -v \
        --run FunctionalTest"""
}
