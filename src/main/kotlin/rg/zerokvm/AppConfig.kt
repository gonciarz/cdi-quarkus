package rg.zerokvm

import arrow.core.Either
import javax.enterprise.context.ApplicationScoped
import javax.inject.Named

typealias AssignKeyboard = suspend (String) -> Either<KeyboardError, Unit>
typealias AssignMouse = suspend (String) -> Either<MouseError, Unit>

sealed interface KeyboardError {
    object ExecutionError : KeyboardError
}

sealed interface MouseError {
    object ExecutionError : MouseError
}

class AppConfig {

    @ApplicationScoped
    @Named(BeanName.ASSIGN_KEYBOARD)
    fun assignLocalKeyboardBean(shellAdapter: ShellAdapter): AssignKeyboard =
        shellAdapter::assignKeyboard

    @ApplicationScoped
    @Named(BeanName.ASSIGN_MOUSE)
    fun assignLocalMouseBean(shellAdapter: ShellAdapter): AssignMouse =
        shellAdapter::assignMouse
}

object BeanName {
    const val ASSIGN_KEYBOARD = "AssignKeyboard"
    const val ASSIGN_MOUSE = "AssignMouse"
}