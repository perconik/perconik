# PerConIK Eclipse Integration

User activity tracking in Eclipse.

## Requirements

- Java 1.7
- Eclipse 4.3
- Maven 3.2
- Tycho 0.20

### Optional

- Elasticsearch 1.2
- User Activity Client Application 2.0

## Building

```
mvn clean install
```

### Non-OSGi Dependencies

```
cd sk.stuba.fiit.perconik.libraries/download
run
```

## Versioning

```
mvn -DnewVersion=1.0.0-SNAPSHOT tycho-versions:set-version tycho-versions:update-pom
```

## Acknowledgement

This Software is the partial result of the Research and Development
Operational Programme for the project Research of methods for acquisition,
analysis and personalized conveying of information and knowledge,
ITMS 26240220039, co-funded by the ERDF.

## License

This software is released under the [MIT License](LICENSE.md).
