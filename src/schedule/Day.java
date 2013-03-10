package src.schedule;

import java.util.ArrayList;
import java.util.List;

public class Day {
	
	public static final int MINUTES_PER_DAY = 60 * 24;
	
	private List<Block> blocks = new ArrayList<Block>();
	
	public boolean add(Block block) {
		block.setDay(this);
		return this.blocks.add(block);
	}
	
	public boolean remove(Block block) {
		block.setDay(null);
		return this.blocks.remove(block);
	}
	
	public boolean contains(Event block) {
		return this.blocks.contains(block);
	}
	
}
