/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  HL30407
 * Created: Apr 6, 2017
 */

DELIMITER //
CREATE PROCEDURE sp_updateStock(IN idIn INT,IN total DOUBLE)
BEGIN 
  DECLARE  currentStock DOUBLE;
  SELECT inStockCount INTO currentStock FROM medicines WHERE id = idIn;
  UPDATE medicines SET inStockCount  = (currentStock-total) WHERE id = idIn;  
END //
DELIMITER ;