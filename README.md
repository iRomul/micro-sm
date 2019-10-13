# Micro SM: Simplest State Machine

Micro SM is a simple implementation of state machine.

Features:

* Stateful objects.
* Configurable transitions constraints with direct transitions and choices.
* Transition events.

## Adding Micro SM to your build

To add a dependency on Micro SM using Maven, use the following:
```xml
<dependency>
  <groupId>io.github.iromul.microsm</groupId>
  <artifactId>micro-sm</artifactId>
  <version>1.0.2</version>
</dependency>
```

To add a dependency using Gradle:

```groovy
dependencies {
  implementation 'io.github.iromul.microsm:micro-sm:1.0.1'
}
```

## Examples

Configuring transitions with ManagedLifecycle:
```java
public void lifecyclePhase() {
    withTransitions()
            .transition(State.STATE_1, State.STATE_2)
            .transition(State::isAllowedState, State.STATE_3)
            .transit();
}
```

Configuring transitions with choice:
```java
public void lifecyclePhase(boolean condition) {
    withTransitions()
            .transition(State.STATE_1,
                    choice()
                            .when(() -> condition, State.STATE_2)
                            .otherwise(State.STATE_3))
            .transit();
}
```
