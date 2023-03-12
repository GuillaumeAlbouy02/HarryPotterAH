package org.HarryPotter.levels;

import lombok.Getter;
import lombok.Setter;
import org.HarryPotter.Position;

public class Cell {
private Position position;

private @Getter @Setter Character character;



private @Getter @Setter boolean walkable;
}
