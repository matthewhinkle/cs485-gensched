package com.cs485.gensched;

import java.util.List;

public interface Event {
	
	public boolean intersects(Event event);
	
	public void addBlockForDay(Day day, Block block);
	
	public void removeBlockForDay(Day day, Block block);
	
	public void removeAllBlocksForDay(Day day);
	
	public List<Block> blocksForDay(Day day);
	
}
