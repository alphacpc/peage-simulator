import random
import time
from datetime import datetime

tabCars = ["C1", "C2", "C3", "C4"]
tabCheckPoints = ["Techno pole", "Poste Thiaroye", "Keur Massar", "Rufisque", "Diamniadio"]
tabPayment = ["Rapido", "Caisse", "Caisse"]

localtime = time.asctime(time.localtime(time.time()))
today = datetime.today().strftime('%Y/%m/%d')



while True:

    print(random.choice(tabCars), random.choice(tabCheckPoints), random.choice(tabPayment), today, localtime )

    time.sleep(0.1)