# -_serie_numerica_6_en_6_con_oracle_19C .

<img width="1024" height="1536" alt="image" src="https://github.com/user-attachments/assets/4dd3b5e6-8f17-4776-90e6-3912db42a80e" />    

# 📌 Serie Numérica 6 en 6 con Oracle 19c:.

Este proyecto genera una serie de **80 números**, aumentando **de 6 en 6**, y realiza tres tareas automáticas:

✅ Genera la serie  
✅ Guarda el resultado en un archivo `.txt`  
✅ Inserta cada número dentro de una tabla en **Oracle 19c**  

Funciona perfecto en **IntelliJ IDEA** o cualquier IDE Java.

---

## 🧱 1. Crear la tabla en Oracle

Ejecuta este script en SQL Developer, SQLPlus o cualquier cliente:

```sql
CREATE TABLE SERIE_NUMEROS (
    ID NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    VALOR NUMBER NOT NULL
);
```

---

## 🧩 2. Dependencia JDBC (Maven)

Agrega el driver en tu `pom.xml`:

```xml
<dependency>
    <groupId>com.oracle.database.jdbc</groupId>
    <artifactId>ojdbc8</artifactId>
    <version>21.5.0.0</version>
</dependency>
```

Si no usas Maven, descarga `ojdbc8.jar` y agréguelo manualmente al proyecto.

---

## ✅ 3. Código completo en Java

Este programa genera la serie, la guarda en un `.txt` y también la inserta en Oracle.

```java
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SerieSeisEnSeisDB {

    public static void main(String[] args) {
        String filePath = "serie_6_en_6.txt";

        int numero = 0;
        int cantidad = 80;

        try {
            FileWriter writer = new FileWriter(filePath);
            Connection conn = conectarOracle();

            if (conn == null) {
                System.out.println("❌ No se pudo conectar a Oracle");
                return;
            }

            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO SERIE_NUMEROS (VALOR) VALUES (?)"
            );

            System.out.println("📝 Generando serie y guardando...");

            for (int i = 0; i < cantidad; i++) {
                writer.write(numero + "\n");

                stmt.setInt(1, numero);
                stmt.executeUpdate();

                numero += 6;
            }

            writer.close();
            stmt.close();
            conn.close();

            System.out.println("✅ Serie guardada en archivo: " + filePath);
            System.out.println("✅ Serie insertada en Oracle correctamente");

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static Connection conectarOracle() {
        try {
            String url = "jdbc:oracle:thin:@localhost:1521:XE"; // Ajustar según tu BD
            String user = "TU_USUARIO";
            String pass = "TU_PASSWORD";

            return DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
```

---

## 🔧 4. Configura tu conexión antes de ejecutar

Reemplaza estos valores según tu entorno:

| Parámetro | Tu ajuste |
|----------|-----------|
| url | Host, puerto y servicio/DB de Oracle |
| user | Nombre de usuario Oracle |
| pass | Contraseña Oracle |

Ejemplo típico para Oracle 19c XE:

```
jdbc:oracle:thin:@localhost:1521/XEPDB1
```

---

## 🎯 Resultado Esperado

• Archivo creado: **serie_6_en_6.txt**  
• 80 números insertados en Oracle  
• Todo automatizado sin dramas 🚀

---

## ✨ Bonus

Si deseas, puedo agregar:  
• GUI gráfica re coqueta en Swing  
• Logs estilo profesional  
• Control de duplicados antes de insertar  
• Docker para Oracle XE  
• Validaciones de conexión y propiedades externas :. 
