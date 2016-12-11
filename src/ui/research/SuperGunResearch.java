package ui.research;

import model.ProjectilePlayerBullet;
import model.RenderableHolder;

public class SuperGunResearch extends ResearchItem {
	public SuperGunResearch() {
		// TODO Auto-generated constructor stub
		super(RenderableHolder.projectile_bullet_img,"Make your gun even more powerful",new int[]{0,3,3,0,0});
	}
	@Override
	public void onResearchSuccess() {
		ProjectilePlayerBullet.addDamage(20);
	}
}