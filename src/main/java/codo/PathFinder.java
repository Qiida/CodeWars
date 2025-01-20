package codo;

import java.util.HashMap;
import java.util.Random;
import java.util.Stack;

public class PathFinder {
    static int pathFinder(String maze) {
        Mountain mountain = new Mountain(maze);
        Path path = new Path(mountain.start(), mountain.goal(), mountain);
        return path.climbs;
    }
}

enum Direction {
    NORTH, EAST, SOUTH, WEST
}

class Mountain {
    final Area[][] graph;
    final int size;
    Mountain(String maze) {
        String[] rows = maze.split("\n");
        this.size = rows.length;
        graph = new Area[size][size];
        for (int y=0; y<size; y++) {
            char[] altitude_chars = rows[y].toCharArray();
            for (int x=0; x<size; x++) {
                char altitude_char = altitude_chars[x];
                String altitude_string = String.valueOf(altitude_char);
                int altitude = Integer.parseInt(altitude_string);
                graph[y][x] = new Area(x, y, altitude, this);
            }
        }
        for (int y=0; y<size;y++) {
            for (int x=0; x<size; x++) {
                graph[y][x].determineClimbsInAllPossibleDirections();
            }
        }
    }
    Area start() {
        return graph[0][0];
    }
    Area goal() {
        return graph[size-1][size-1];
    }
}

class Area {
    final int x;
    final int y;
    final int altitude;
    final HashMap<Direction, Integer> directionClimbsMap = new HashMap<>();
    final Mountain mountain;
    Area(int x, int y, int altitude, Mountain mountain) {
        this.x = x;
        this.y = y;
        this.altitude = altitude;
        this.mountain = mountain;
    }

    public void determineClimbsInAllPossibleDirections() {
        if (lookNorth()) {
            directionClimbsMap.put(Direction.NORTH, getClimbs(Direction.NORTH));
        }
        if (lookEast()) {
            directionClimbsMap.put(Direction.EAST, getClimbs(Direction.EAST));
        }
        if (lookSouth()) {
            directionClimbsMap.put(Direction.SOUTH, getClimbs(Direction.SOUTH));
        }
        if (lookWest()) {
            directionClimbsMap.put(Direction.WEST, getClimbs(Direction.WEST));
        }
    }

    int getClimbs(Direction direction) {
        return switch (direction) {
            case NORTH -> {
                Area next = getNorth();
                yield getClimb(this, next);
            }
            case EAST -> {
                Area next = getEast();
                yield getClimb(this, next);
            }
            case SOUTH -> {
                Area next = getSouth();
                yield getClimb(this, next);
            }
            case WEST -> {
                Area next = getWest();
                yield getClimb(this, next);
            }
            default -> 0;
        };
    }

    boolean lookNorth() {
        return y > 0;
    }

    boolean lookEast() {
        return x < mountain.size - 1;
    }

    boolean lookSouth() {
        return y < mountain.size - 1;
    }


    boolean lookWest() {
        return x > 0;
    }


    Area getNorth() {
        return mountain.graph[y - 1][x];
    }

    Area getEast() {
        return mountain.graph[y][x+1];
    }

    Area getSouth() {
        return mountain.graph[y+1][x];
    }

    Area getWest() {
        return mountain.graph[y][x-1];
    }

    private static int getClimb(Area current, Area next) {
        int climb = current.altitude - next.altitude;
        if (climb < 0) {
            climb *= -1;
        }
        return climb;
    }
}

class Path {
    final Stack<Area> walked = new Stack<>();
    final Mountain mountain;
    int climbs = 0;
    final static Random random = new Random();

    Path(Area start, Area goal, Mountain mountain) {
        this.mountain = mountain;
        walked.push(start);

        Area current = walked.peek();
        Direction directionToWalk = null;
        Integer lowestClimbs = null;
        Direction directionCameFrom = null;
        while(!reachedGoal(goal, current)) {
            for (Direction direction : current.directionClimbsMap.keySet()) {
                if (directionCameFrom != direction) {
                    if (directionToWalk == null) {
                        directionToWalk = direction;
                        lowestClimbs = current.directionClimbsMap.get(directionToWalk);
                    } else {
                        int climbs = current.directionClimbsMap.get(direction);
                        if (climbs < lowestClimbs) {
                            lowestClimbs = climbs;
                            directionToWalk = direction;
                        } else if (climbs == lowestClimbs) {
                            int flip = random.nextInt(2);
                            if (flip == 0) {
                                directionToWalk = direction;
                            }
                        }
                    }
                }
            }
            directionCameFrom = walk(directionToWalk);
            directionToWalk = null;
            current = walked.peek();
        }
    }

    private static boolean reachedGoal(Area goal, Area current) {
        return (current.x == goal.x) && (current.y == goal.y);
    }

    Direction walk(Direction direction) {
        Area current = walked.peek();
        return switch (direction) {
            case NORTH -> {
                walkNorth(current);
                yield Direction.SOUTH;
            }
            case EAST -> {
                walkEast(current);
                yield Direction.WEST;
            }
            case SOUTH -> {
                walkSouth(current);
                yield Direction.NORTH;
            }
            case WEST -> {
                walkWest(current);
                yield Direction.EAST;
            }
        };
    }
    void walkNorth(Area current) {
        Area next = current.getNorth();
        climbs += current.getClimbs(Direction.NORTH);
        walked.push(next);
    }

    void walkEast(Area current) {
        Area next = current.getEast();
        climbs += current.getClimbs(Direction.EAST);
        walked.push(next);
    }

    void walkSouth(Area current) {
        Area next = current.getSouth();
        climbs += current.getClimbs(Direction.SOUTH);
        walked.push(next);
    }

    void walkWest(Area current) {
        Area next = current.getWest();
        climbs += current.getClimbs(Direction.WEST);
        walked.push(next);
    }
}



