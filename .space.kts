job("Hello world") {
    container("hello-world"){
        args("gradle", "build")
    }
}