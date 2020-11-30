#include <LiquidCrystal_I2C.h>
#include "DHT.h"

#define DHTPIN A0
#define DHTTYPE DHT11

LiquidCrystal_I2C lcd(0x27,16,2);
DHT dht(DHTPIN, DHTTYPE);

const int VENTILADOR = 3;
const int UMIDIFICADOR = 2;

bool isModoAutomatico;
const float temperaturaLigarVentilador = 27; 
const float umidadeLigarUmidificador = 60; 

float temperatura; 
float umidade; 
int contador = 0;
String serialInput;
 
void setup()
{  
  Serial.begin(9600);
  lcd.init();  
  dht.begin();
  pinMode(VENTILADOR, OUTPUT);
  pinMode(UMIDIFICADOR, OUTPUT);        
  desligarVentilador();
  desligarUmidificador();      
  isModoAutomatico = false;
}
 
void loop()
{          
  lcd.setBacklight(HIGH);
  umidade = dht.readHumidity();
  temperatura = dht.readTemperature();
    
  contador++;
  printDataOnSerial(temperatura, umidade);

  if(Serial.available()){
    processaDadosRecebidosSerial();
 } 
  
  if(contador == 10) {
    contador = 0;
  }  

  if (isModoAutomatico){
    ativaModoAutomatico();
  }
  
  if(contador == 1){
    printMainOnDisplay();        
  }
  else{
    printDataOnDisplay(temperatura, umidade);          
  }
  delayEmSegundos(5);
}

void printMainOnDisplay(){
    lcd.clear();        
    lcd.setCursor(1, 0);  
    lcd.print("FIAP IoT");
    lcd.setCursor(0, 1);
    if (isModoAutomatico){
      lcd.print("Modo Auto Ativo");             
    }else{
      lcd.print("Modo Auto Inativ");             
    }        
}

void printDataOnDisplay(float temperatura, float umidade){
    lcd.clear();        
    lcd.setCursor(1, 0);    
    lcd.print("Temp. " + (String)temperatura);
    lcd.setCursor(1, 1);
    lcd.print("Umidade. " + (String)umidade);     
}

void printDataOnSerial(float temperatura, float umidade){
    Serial.print("T:" + (String)temperatura + ",U:" + (String)umidade + ";");   
}

void processaDadosRecebidosSerial(){
    serialInput = Serial.readStringUntil(';');  

    if (serialInput == "LIGAR_VENTILADOR"){               
      ligarVentilador(); 
      return;
    }
    
    if (serialInput == "DESLIGAR_VENTILADOR"){
      desligarVentilador(); 
      return;
    }

    if (serialInput == "LIGAR_UMIDIFICADOR"){               
      ligarUmidificador(); 
      return;
    }
    
    if (serialInput == "DESLIGAR_UMIDIFICADOR"){
      desligarUmidificador(); 
      return; 
    }

     if (serialInput == "ATIVAR_MODO_AUTOMATICO"){
      isModoAutomatico = true;      
      return; 
    }

     if (serialInput == "DESATIVAR_MODO_AUTOMATICO"){
      isModoAutomatico = false;
      return; 
    }

}

void ligarVentilador(){
   digitalWrite(VENTILADOR, LOW);
}

void desligarVentilador(){
   digitalWrite(VENTILADOR, HIGH);
}

void ligarUmidificador(){
   digitalWrite(UMIDIFICADOR, LOW);
}

void desligarUmidificador(){
   digitalWrite(UMIDIFICADOR, HIGH);
}

void delayEmSegundos(int segundos){   
  delay(segundos * 1000);
}

void ativaModoAutomatico(){
  if(temperatura > temperaturaLigarVentilador ){
    ligarVentilador();
  }else{
    desligarVentilador(); 
  }
  
  if (umidade > umidadeLigarUmidificador ){
    ligarUmidificador();
  }
  else{
    desligarUmidificador();
  }  
}
