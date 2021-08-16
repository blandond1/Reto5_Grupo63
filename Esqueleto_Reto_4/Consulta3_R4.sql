SELECT m.Nombre_Material,
m.Importado,
COUNT(*) AS No_Compras
FROM MaterialConstruccion m
JOIN Compra c
ON m.ID_MaterialConstruccion = c.ID_MaterialConstruccion
WHERE m.Importado = 'Si'
GROUP BY c.ID_MaterialConstruccion
ORDER BY No_Compras