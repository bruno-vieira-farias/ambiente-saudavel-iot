#include <LiquidCrystal_I2C.h>
#include "DHT.h"

#define DHTPIN A0 // pino que estamos conectado
#define DHTTYPE DHT11 // DHT 11

LiquidCrystal_I2C lcd(0x27,16,2);
DHT dht(DHTPIN, DHTTYPE);

const int VENTILADOR = 3;
const int UMIDIFICADOR = 2;

float temperatura; 
float umidade; 
int contador = 0;
String input;
 
void setup()
{  
    Serial.begin(9600);
    lcd.init();  
    dht.begin();
    pinMode(VENTILADOR, OUTPUT);
    pinMode(UMIDIFICADOR, OUTPUT);        
    desligarVentilador();
   desligarUmidificador();  
    
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
//
//  if (temperatura > 28){
//    digitalWrite(pinLed, HIGH); 
//  }else{
//    digitalWrite(pinLed, LOW);  
//  }  
  
  if(contador == 1 || contador == 2){
    printMainOnDisplay();        
  }
  else{
    printDataOnDisplay(temperatura, umidade);          
  }
  delayEmSegundos(10);
}


void printMainOnDisplay(){
    lcd.clear();        
    lcd.setCursor(1, 0);  
    lcd.print("FIAP IOT 35SCJ");
    lcd.setCursor(2, 1);
    lcd.print("Bruno Farias");           
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
    input = Serial.readStringUntil(';');  

    if (input == "LIGAR_VENTILADOR"){               
      ligarVentilador(); 
      return;
    }
    
    if (input == "DESLIGAR_VENTILADOR"){
      desligarVentilador(); 
      return;
    }

    if (input == "LIGAR_UMIDIFICADOR"){               
      ligarUmidificador(); 
      return;
    }
    
    if (input == "DESLIGAR_UMIDIFICADOR"){
      desligarUmidificador(); 
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
