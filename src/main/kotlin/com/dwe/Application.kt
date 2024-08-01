package com.dwe

import com.dwe.plugins.configureSecurity
import com.dwe.plugins.configureSerialization
import com.dwe.repository.UserRepository
import com.dwe.routing.configureRouting
import com.dwe.service.JwtService
import com.dwe.service.UserService
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main(args: Array<String>): Unit = EngineMain.main(args)


fun Application.module() {
    val userRepository = UserRepository()
    val userService = UserService(userRepository)
    val jwtService = JwtService(this, userService)

    configureSecurity(jwtService)
    configureRouting(userService, jwtService)
    configureSerialization()
}
