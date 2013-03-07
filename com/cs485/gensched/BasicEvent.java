package com.cs485.gensched;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class BasicEvent implements Event {
	
	private final Map<Day, List<Block>> blocksByDay = new HashMap<Day, List<Block>>();
	
	@Override
	public boolean intersects(Event event) {
		for(Entry<Day, List<Block>> entry : this.blocksByDay.entrySet()) {
			final Day day = entry.getKey();
			
			final List<Block> blocks = entry.getValue();
			final List<Block> eventBlocks = event.blocksForDay(day);
			for(Block block : blocks) {
				
				for(Block eventBlock : eventBlocks) {
					final int start = block.getStartMinute();
					final int end = block.getEndMinute();
					
					final int eventStart = eventBlock.getStartMinute();
					final int eventEnd = eventBlock.getEndMinute();
					
					if(start > eventStart && start < eventEnd || end > eventStart && end < eventEnd) {
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	@Override
	public void addBlockForDay(Day day, Block block) {
		List<Block> blocks = this.blocksForDay(day);
		if(blocks == null) {
			blocks = new ArrayList<Block>();
			this.blocksByDay.put(day, blocks);
		}
		
		blocks.add(block);
	}
	
	@Override
	public void removeBlockForDay(Day day, Block block) {
		final List<Block> blocks = this.blocksForDay(day);
		if(blocks == null) {
			return;
		}
		
		blocks.remove(block);
	}
	
	@Override
	public void removeAllBlocksForDay(Day day) {
		final List<Block> blocks = this.blocksForDay(day);
		if(blocks == null) {
			return;
		}
		
		blocks.clear();
	}
	
	@Override
	public List<Block> blocksForDay(Day day) {
		return this.blocksByDay.get(day);
	}
	
}
