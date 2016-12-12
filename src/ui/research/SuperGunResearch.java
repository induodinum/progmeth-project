package ui.research;

import model.ProjectilePlayerBullet;
import model.RenderableHolder;

public class SuperGunResearch extends ResearchItem {
	public SuperGunResearch() {
		super(RenderableHolder.research_super_gun,"Make your gun even more powerful",new int[]{0,3,3,0,0});
	}
	@Override
	public void onResearchSuccess() {
		ProjectilePlayerBullet.addDamage(40);
	}
}
