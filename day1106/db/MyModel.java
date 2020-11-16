/*
 * MVC패턴이 적용된 JTabledptj tkdydehlsms TableModel은 명칭으로는 마치 모델인것처럼 보이지만
 * 그 역할로 본다면 컨트롤러이다!!
 * 
 *JTable(View) ----------컨트롤러(TableModel)----------------데이터(Model)
 *
 *MyModel이 보유한 모든 메서드 호출자는? JTable이다!
 * 
 */
package day1106.db;

import javax.swing.table.AbstractTableModel;

public class MyModel extends AbstractTableModel{
	
	//데이터 준비
	String [][] flower = {
			{"장미","50000","RED","Korea"},	
			{"튤립","70000","Purple","Korea"},	
			{"안개꽃","35000","White","Korea"}
			
	};
	String [][] car = {
			{"BMW","7000","White"},	
			{"Audi","12000","Black"},	
			{"Benz","10000","Silver"}
			
	};

	//행의 갯수
	public int getRowCount() {
		
		return car.length;
	}

	//열의 갯수
	public int getColumnCount() {
		
		return car[0].length;
	}

	//지정한 행열에 해당하는 값반환
	public Object getValueAt(int row, int col) {
		System.out.println("row:"+row+",col :"+col);
		return car[row][col];
	}

}
