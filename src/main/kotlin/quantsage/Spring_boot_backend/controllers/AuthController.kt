package quantsage.Spring_boot_backend.controllers

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import quantsage.Spring_boot_backend.security.AuthService

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService,

) {
    data class AuthRequest(
        val email: String,
        val password: String

    )
    data class RefreshRequest(
        val refreshToken: String

    )

    @PostMapping("/register")
    fun register(
        @RequestBody body: AuthRequest
    ){
        authService.register(body.email, body.password)
    }
    @PostMapping("/login")
    fun login(
        @RequestBody body: AuthRequest
    ) {
        authService.login(body.email, body.password)
    }
    @PostMapping("/refresh")
    fun refresh(
        @RequestBody body: RefreshRequest
    ){
        authService.refresh(body.refreshToken)
    }
}