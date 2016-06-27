package com.realestate.api.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by trodrigues on 26/06/16.
 */
public enum Province {
	GODE("Gode", new Point(0,1000), new Point(600,500)),
	RUJA("Ruja", new Point(400,1000), new Point(1100,500)),
	JABY("Jaby", new Point(1100,1000), new Point(1400,500)),
	SCAVY("Scavy", new Point(0,500), new Point(600,0)),
	GROOLA("Groola", new Point(600,500), new Point(800,0)),
	NOVA("Nova", new Point(800,500), new Point(1400,0));

	private String name;

	private Area area;

	Province(String name, Point upperLeft, Point bottomRight) {
		this.name = name;
		this.area = new Area(upperLeft, bottomRight);
	}

	public String getName() {
		return name;
	}

	protected Area getArea() {
		return area;
	}

	public static Collection<Province> listProvinces(Area area) {
		return Stream.concat(
				listProvinces(area.getUpperLeft()).stream(),
				listProvinces(area.getBottomRight()).stream())
				.collect(Collectors.toList());
	}

	public static Collection<Province> listProvinces(Point point) {
		return listProvinces(point.getX(), point.getY());
	}

	public static Collection<Province> listProvinces(Integer x, Integer y) {

		return Arrays.stream(values())
				.filter(province -> province.getArea().isInRange(x,y))
				.collect(Collectors.toList());
	}
}
