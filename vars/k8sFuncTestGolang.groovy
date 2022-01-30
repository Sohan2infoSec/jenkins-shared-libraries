def call(project, addr) {
    sh "go get -d -v -t"
    sh """DURATION=1 ADDRESS=${addr} \
        go test ./... -v \
        --run FunctionalTest"""
}
