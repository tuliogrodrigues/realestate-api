package com.realestate.api.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by trodrigues on 27/06/16.
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class Area {

	private Point upperLeft;

	private Point bottomRight;

	public Area(Point upperLeft, Point bottomRight) {
		this.upperLeft = upperLeft;
		this.bottomRight = bottomRight;
	}

	public Area(Integer ax, Integer ay, Integer bx, Integer by) {
		this.upperLeft = new Point(ax, ay);
		this.bottomRight = new Point(bx, by);
	}

	public void setAx(Integer ax) {
		if(upperLeft == null) {
			upperLeft = new Point();
		}
		upperLeft.setX(ax);
	}

	public void setAy(Integer ay) {
		if(upperLeft == null) {
			upperLeft = new Point();
		}
		upperLeft.setY(ay);
	}

	public void setBx(Integer bx) {
		if(bottomRight == null) {
			bottomRight = new Point();
		}
		bottomRight.setX(bx);
	}

	public void setBy(Integer by) {
		if(bottomRight == null) {
			bottomRight = new Point();
		}
		bottomRight.setY(by);
	}

	public boolean isInRange(Integer x, Integer y) {
		return isXInRange(x) && isYInRange(y);
	}

	private boolean isXInRange(Integer x) {
		return (x != null) && (upperLeft.getX() <= x) && (bottomRight.getX() >= x);
	}

	private boolean isYInRange(Integer y) {
		return(y != null) && (bottomRight.getY() <= y) && (upperLeft.getY() >= y);
	}
}
