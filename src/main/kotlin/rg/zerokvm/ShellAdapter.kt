package rg.zerokvm

import arrow.core.Either
import arrow.core.right
import kotlinx.coroutines.delay
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class ShellAdapter {

    suspend fun assignKeyboard(computer: String): Either<KeyboardError, Unit> {
        delay(100)
        return Unit.right()
    }

    suspend fun assignMouse(computer: String): Either<MouseError, Unit> {
        delay(100)
        return Unit.right()
    }

}