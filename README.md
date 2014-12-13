# PerConIK Eclipse Integration

User activity tracking in Eclipse.

## Requirements

- Java 1.7
- Eclipse 4.3
- Maven 3.2
- Tycho 0.20

### Optional

- [User Activity Client Application](https://github.com/perconik/uaca) 2.0

## Setup

- Clone [perconik project](https://github.com/perconik/perconik) into workspace
- Clone [perconik update repository](https://github.com/perconik/perconik.github.io) into workspace

## Building

1. Download external libraries with `sk.stuba.fiit.perconik.libraries/download/run`
2. Build project by running `mvn clean install -Dtycho.localArtifacts=ignore`

## Versioning

- Use [version-tiger](https://github.com/inventage/version-tiger) for versioning
- Commit only version changes, never combine source code with version changes
- Start each version change commit with `Bump`
- Do not use Maven Tycho for versioning 

## Contributing

1. Fork it
2. Create your feature branch (`git checkout -b new-feature`)
3. Commit your changes (`git commit -am 'Add some feature'`)
4. Push to the branch (`git push origin new-feature`)
5. Create new Pull Request

## Acknowledgement

This Software is the partial result of the Research and Development
Operational Programme for the project Research of methods for acquisition,
analysis and personalized conveying of information and knowledge,
ITMS 26240220039, co-funded by the ERDF.

## License

This software is released under the [MIT License](LICENSE.md).
