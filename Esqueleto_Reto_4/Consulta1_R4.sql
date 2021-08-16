SELECT c.ID_Proyecto, 
p.Clasificacion,
SUM(c.Cantidad*m.Precio_Unidad) AS Gasto_Compras,
p.Serial
FROM Compra c
JOIN MaterialConstruccion m
ON c.ID_MaterialConstruccion = m.ID_MaterialConstruccion
JOIN Proyecto p
ON p.ID_Proyecto = c.ID_Proyecto
GROUP BY c.ID_Proyecto
ORDER BY Gasto_Compras
LIMIT 5