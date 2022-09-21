from ensurepip import bootstrap
import random
import time
from datetime import datetime
from kafka3 import KafkaProducer

tabCars = ["C1", "C2", "C3", "C4"]
tabCheckPoints = ["Techno pole", "Poste Thiaroye", "Keur Massar", "Rufisque", "Diamniadio"]
tabPayment = ["Rapido", "Caisse", "Caisse"]


producer = KafkaProducer(bootstrap_servers='localhost:9092')


while True:

    now = datetime.now()
    today = datetime.today().strftime('%Y/%m/%d')
    current_time = now.strftime("%H:%M:%S")

    # print(random.choice(tabCars), random.choice(tabCheckPoints), random.choice(tabPayment), today, localtime )
    car = random.choice(tabCars)
    point = random.choice(tabCheckPoints)
    payment = random.choice(tabPayment)

    msg = f"{car},{point},{payment},{today},{current_time}"

    print(msg)

    producer.send("devdata", bytes(msg, encoding='utf8'))

    time.sleep(0.2)