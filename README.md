# sew9-2425-worttrainer-Snorlax0815

A word trainer for SEW 9.

## Dependencies

- org.json:json:20240303
  - Just for JSON Objects and Saving/Loading
- org.mockito:mockito-core:5.13.0
  - For mocking objects in tests
  - Like JOptionPane cuz i couldnt interact with it in tests

## Gradle Plugins

- com.github.johnrengelman.shadow:8.1.1
  - For creating a fat jar
  - This is needed, because it would otherwise throw an java.lang.NoClassDefFoundError

## GitHub Actions

- 

## Save/Load System (Persistence)

The Save/Load (Persistence) System uses the Strategy Pattern to allow for different implementations of saving and loading.
The only implemented Strategy is the JSON Strategy, which saves and loads the data in a JSON file.
The Save file is named `save.json` and is located in the root directory of the project.
This is a sample of the JSON file:

```json
{
  "countFalse": 8,
  "entries": [
    {
      "value": "Mountain",
      "URL": "https://cdn.pixabay.com/photo/2017/02/14/03/03/ama-dablam-2064522_1280.jpg"
    },
    {
      "value": "Lake",
      "URL": "https://cdn.pixabay.com/photo/2019/06/23/05/46/mountains-4292912_1280.jpg"
    }
  ],
  "countCorrect": 14
}
```

## UML Diagram

![UML Diagram](design/design.png)
