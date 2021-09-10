package FortBrasilAdequacaoPCI;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

public class SimulaComprasAntecipaveisTeste {

    @DisplayName("Deve retornar Mensagem de Sucesso")
    @ParameterizedTest
    @CsvFileSource(resources = "/massaDeTestes/parametrosConsultaSucesso.csv", numLinesToSkip = 1, delimiter = ';')
    public void testeListaComprasAntecipaceisSucesso(String idEstabelecimento ) {

        System.out.println(idEstabelecimento);


        given().
                header("access_token", "fortbrasilNew").
                log().all().
                when().
                get("http://10.75.130.9:8181/v2/api/estabelecimentos/" + idEstabelecimento + "/compras/antecipaveis").
                then().
                assertThat().
                log().all().
                statusCode(HttpStatus.SC_OK)
        ;
    }

    @DisplayName("Deve retornar erro de Bad Request devido a parâmetros inválidos")
    @ParameterizedTest
    @CsvFileSource(resources = "/massaDeTestes/parametrosConsultaBadRequest.csv", numLinesToSkip = 1, delimiter = ';')
    public void testeListaComprasAntecipaceisBadRequest(String idEstabelecimento, String page, String limit ) {

        System.out.println(idEstabelecimento);
        System.out.println(page);
        System.out.println(limit);

        given().
                header("access_token", "fortbrasilNew").
                param("page", page).
                param("limit", limit).
                log().all().
                when().
                get("http://10.75.130.9:8181/v2/api/estabelecimentos/" + idEstabelecimento + "/compras/antecipaveis").
                then().
                assertThat().
                log().all().
                statusCode(HttpStatus.SC_BAD_REQUEST)
        ;
    }

    @DisplayName("Deve retornar erro Not Found devido a parâmetro inválido")
    @ParameterizedTest
    @CsvFileSource(resources = "/massaDeTestes/parametrosConsultaNotFound.csv", numLinesToSkip = 1, delimiter = ';')
    public void testeListaComprasAntecipaceisNotFound(String idEstabelecimento, String page, String limit ) {

        System.out.println(idEstabelecimento);
        System.out.println(page);
        System.out.println(limit);

        given().
                header("access_token", "fortbrasilNew").
                param("page", page).
                param("limit", limit).
                log().all().
                when().
                get("http://10.75.130.9:8181/v2/api/estabelecimentos/" + idEstabelecimento + "/compras/antecipaveis").
                then().
                assertThat().
                log().all().
                statusCode(HttpStatus.SC_NOT_FOUND)
        ;
    }

}
