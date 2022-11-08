# CSTV

Um pequeno aplicativo que lista partidas de CS:GO, criado usando Kotlin.

## Especificações

O aplicativo possui uma splashscreen e duas telas: uma página inicial que lista partidas agendadas
de CS:GO, e uma página de detalhes que lista os detalhes e jogadores de cada partida.

Os dados usados pelo app são consumidos da API [PandaScore](https://pandascore.co/).

## Como executar o projeto

* Clone o projeto para seu Android Studio.
* Gere uma cópia do arquivo `secrets.defaults.properties` com o nome `secrets.properties` na *root*
do projeto.
* Substitua `"YOUR_API_KEY"` por uma chave válida da API *PandaScore*
    * Obs.: chaves gratuitas da API não dão acesso à busca detalhada de partidas, então a tela de
  detalhes irá carregar indefinidamente. Ainda é possível ver um preview da tela através do Android
  Studio, na aba Design do arquivo `MatchDetailsScreen.kt`
* Execute o projeto.

## Especificações técnicas

* O projeto foi desenvolvido em Kotlin.
* Para trabalhos assíncronos foi utilizado Coroutines.
* Retrofit e kotlinx.serialization foram utilizados para chamadas HTTP. Coil foi utilizado para 
carregamento de imagens.
* A UI foi desenvolvida usando Jetpack Compose.
* A splashscreen foi criada usando a [SplashScreen API](https://developer.android.com/develop/ui/views/launch/splash-screen)
* Para lidar com datas e fuso horários foi utilizado a biblioteca do Java Time.
* Para injeção de dependências foi utilizado o Hilt.
* Foram implementados testes unitários; foi utilizado o Kotest para asserções idiomáticas e o MockK
para criação de mocks.

## Arquitetura

* O projeto segue os princípios da Clean Architecture, que fundamentalmente separa as classes em 3
camadas: dados (data), domínio (domain) e apresentação (presentation).

    * Domain: contém as classes fundamentais para as regras de negócio do app, não contendo nenhuma
    dependência em outros frameworks ou na infraestrutura do app. Define as entidades usadas no app
    e contratos como a interface dos repositórios.

    * Data: camada responsável por obter os dados usados no restante do app. Define DTOs usados para
    receber dados de APIs externas. Implementa o contrato dos repositórios para
    poder fornecer dados às demais camadas ao mesmo tempo que abstrai a origem desses dados.

    * Presentation: camada responsável por receber as interações do usuário, demonstrar informações
    e se comunicar com a camada de domínio. Não possui regras complexas, apenas segue as regras já
    definidas na camada de domínio.

* A camada de apresentação segue a arquitetura MVVM, onde cada tela (composable) possui um
view model responsável por gerenciar seu estado. O view model acessa a camada de domínio, movendo 
maior parte da lógica para fora da UI.
