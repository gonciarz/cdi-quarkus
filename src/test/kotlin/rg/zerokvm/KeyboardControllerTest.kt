package rg.zerokvm

import io.quarkiverse.test.junit.mockk.InjectMock
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured
import io.restassured.http.ContentType
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.enterprise.inject.spi.CDI
import javax.enterprise.util.TypeLiteral
import javax.inject.Inject
import javax.inject.Named

@QuarkusTest
internal class KeyboardControllerTest {

//    @InjectMock // Doesn't work with generic type
//    @Named(BeanName.ASSIGN_KEYBOARD)
//    private lateinit var assignKeyboard: AssignKeyboard

//    @Inject // Doesn't work: AmbiguousResolutionException
//    @Named(BeanName.ASSIGN_KEYBOARD)
//    private lateinit var assignKeyboard: AssignKeyboard

    @Test
    fun `should find bean by TypeLiteral`() {
        val typeLiteral = object : TypeLiteral<AssignKeyboard>() {}
        val beanInstance = CDI.current().select(typeLiteral)
        assertNotNull(beanInstance)
    }

    @Test
    fun `should switch computer for keyboard`() {
        RestAssured.given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .body(SwitchKeyboardDto("Computer1"))
            .`when`().post("/api/keyboard")
            .then()
            .statusCode(202)
    }

}
