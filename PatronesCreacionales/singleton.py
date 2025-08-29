import sqlite3
import threading

class DatabaseConnection:
    _instance = None
    _lock = threading.Lock() 

    def __new__(cls, db_name):
        with cls._lock:
            if cls._instance is None:
                cls._instance = super().__new__(cls)
                cls._instance._initialized = False
        return cls._instance

    def __init__(self, db_name):
        if getattr(self, "_initialized", False):
            return  
        self.connection = sqlite3.connect(db_name)
        self.cursor = self.connection.cursor()
        self._initialized = True

    def query(self, sql, params=()):
        self.cursor.execute(sql, params)
        self.connection.commit()

    def fetchall(self):
        return self.cursor.fetchall()

    def close(self):
        try:
            self.connection.close()
        finally:
            type(self)._instance = None


db = DatabaseConnection("database.db")
db.query("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT)")
db.query("INSERT INTO users (name) VALUES (?)", ("Isaac Tonato",))
db.query("INSERT INTO users (name) VALUES (?)", ("Eduardo Tonato",))


db2 = DatabaseConnection("database.db")
db2.query("SELECT * FROM users")
print(db2.fetchall())  

db.close()
