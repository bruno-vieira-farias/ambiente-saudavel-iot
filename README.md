# Ambiente Saudável

Um projeto com objetivo de manter o ambiente com uma temperatura e umidade adequada e controle remoto dos equipamentos.

### Vídeo Apresentação
[Clique_Aqui](https://youtu.be/497JHrPEmwQ)  

### Topologia do Projeto
![Topologia_projeto](/doc/images/TopologiaProjeto.png)

### Esquema Físico Detalhado
![Esquema_Fisico_Detalhado](/doc/images/EsquemaFisicoDetalhado.png)

### Materiais Utilizados.
- Arduino Uno.
- Protoboard.
- Display LCD 16x2.
- Módulo Adaptador I2C Para Display LCD.
- Sensor DHT11. (Temperatura e Umidade).
_ Módulo Rele 2 canais.
- Cabos 0,2mm (Jumpers).
- Cabos 2,5mm.
- Tomada Fêmea.
- Tomada Macho.

### Telegram.
Bot [Telegram](https://telegram.org/) que fornece informações relevantes para investidores.

### Setup
- Crie um [Telegram bot](https://core.telegram.org/bots).
- Insira o token do seu bot no arquivo `application.properties`
```text
// src/main/resources/application.properties

telegram-bot.token={seuTelegramToken}
...
```
### Bot em execução.
![Bot_Telegram](/doc/images/botImage.jpg)

### Run
Execute na raiz do projeto a task `bootRun` do gradle.

- Windows
  `gradlew bootRun`
- Linux / Mac
  `./gradlew bootRun`

### Comandos válidos
- `/start`
- `Obter Temperatura`
- `Obter Umidade`
- `Ligar Ventilador`
- `Desligar Ventilador`
- `Ligar Umidificador`
- `Desligar Umidificador`
- `Ativar modo automatico`
- `Desativar modo automatico`