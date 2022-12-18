package rg.zerokvm

import javax.inject.Named
import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class KeyboardController(
    @Named(BeanName.ASSIGN_KEYBOARD) // TODO: remove named annotation to reproduce AmbiguousResolutionException
    private val assignKeyboard: AssignKeyboard,
) {

    @Path("/keyboard")
    @POST
    suspend fun switch(switchActiveComputer: SwitchKeyboardDto): Response =
        assignKeyboard(switchActiveComputer.computer).fold(
            { Response.serverError().entity(it) },
            { Response.accepted() }
        ).build()
}

data class SwitchKeyboardDto(
    val computer: String,
)
