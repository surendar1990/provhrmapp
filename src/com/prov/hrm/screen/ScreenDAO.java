package com.prov.hrm.screen;

import java.util.List;

public interface ScreenDAO {
	public List<Screen> getAllScreen();

	public Screen getScreenById(int screenId);

	public int addScreen(Screen screen);

	public int updateScreen(Screen screen);

	public int deleteScreen(int screenId);
}
