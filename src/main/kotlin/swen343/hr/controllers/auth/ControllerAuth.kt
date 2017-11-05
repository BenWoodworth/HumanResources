package swen343.hr.controllers.auth

import com.google.inject.Inject
import com.google.inject.Singleton
import spark.RouteGroup
import spark.kotlin.get
import spark.kotlin.post
import swen343.hr.dependencies.HashProvider
import swen343.hr.dependencies.TemplateLoader
import swen343.hr.dependencies.UserService
import swen343.hr.models.User
import swen343.hr.util.Permission
import swen343.hr.util.user
import swen343.hr.viewmodels.ViewModelBasic

/**
 * Created by beltran on 11/01/17.
 */
@Singleton
class ControllerAuth @Inject constructor(
        private val userService: UserService,
        private val hashProvider: HashProvider,
        private val templateLoader: TemplateLoader
) : RouteGroup {

    override fun addRoutes() {

        get("") {
            response.redirect("./login")
        }

        get("/login") {
            templateLoader.loadTemplate(
                    "/auth/login.ftl",
                    ViewModelBasic(session().user())
            )
        }

        post("/login") {
            val username = request.queryParams("username")
            val password = request.queryParams("password")

            val passwordHash = password?.let { hashProvider.hash(password) }
            val user = userService.getUser(username)

            if (user?.passwordHash != passwordHash) {
                response.redirect("/auth") // TODO Invalid login message
            } else {
                session().user(user)
                response.redirect("/")
            }
        }

        get("/sign-out") {
            session().user(null)
            response.redirect("/")
        }

        get("/register") {
            templateLoader.loadTemplate(
                    "/auth/register.ftl",
                    ViewModelBasic(session().user())
            )
        }

        post("/register") {
            val permissions = request
                    .queryParams("permissions")
                    .split(Regex("\\v+"))
                    .map { Permission(it) }

            val user = userService.addUser(User(
                    username = request.queryParams("username"),
                    passwordHash = hashProvider.hash(request.queryParams("password")),
                    permissions = permissions
            ))

            session().user(user)
            response.redirect("/")
        }
    }
}
