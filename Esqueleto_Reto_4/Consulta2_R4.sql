SELECT p.Banco_Vinculado,
AVG(tp.Area_Max) AS Area_Promedio
FROM Proyecto p
INNER JOIN Tipo tp
ON p.ID_Tipo = tp.ID_Tipo
GROUP BY p.Banco_Vinculado
ORDER BY Area_Promedio
