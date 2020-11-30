# Ambiente Saudável

Um projeto com objetivo de ....

### Topologia do Projeto
![Topologia_projeto](/doc/images/TopologiaProjeto.png)

### Esquema Físico Detalhado
![Esquema_Fisico_Detalhado](/doc/images/EsquemaFisicoDetalhado.png)

### Materiais Utilizados.
- Arduino Uno.
- Protoboard.
- Display LCD 16x2.
- Módulo Adaptador I2C Para Display LCD.
- Sensor DHT11. (Temperatura e Umidade)
_ Módulo Rele 2 canais.
- Cabos 0,2mm (Jumpers).
- Cabos 2,5mm.
- Tomada Fêmea.
- Tomada Macho.

### Telegram.
Bot [Telegram](https://telegram.org/) que fornece informações relevantes para investidores.
- TODO - Colocar um print da imagem do bot.


### Setup
- Crie um [Telegram bot](https://core.telegram.org/bots).
- Insira o token do seu bot no arquivo `application.properties`
```text
// src/main/resources/application.properties

telegram-bot.token={seuTelegramToken}
...
```
### Run
Execute na raiz do projeto a task `bootRun` do gradle.

- Windows
  `gradlew bootRun`
- Linux / Mac
  `./gradlew bootRun`

### Tela Inicial 
![Tela Inicial](/images/telaInicial.png)


### Comandos válidos
- `/start`
- `Taxa selic hoje`
- `Taxa selic acumulada nos últimos 30 dias`
- `Rendimento da poupanca nos últimos 30 dias`
- `Rendimento da poupança acumulado nos últimos 12 meses`

### JavaDoc
- Disponível [aqui](https://onurbasfar.github.io/InvestiBot/doc/) ou no diretorio /doc do projeto.