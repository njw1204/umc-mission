SELECT
	*
FROM
	store_menu_category
	INNER JOIN store_menu
	ON
		store_menu.store_menu_category_id = store_menu_category.id
	LEFT OUTER JOIN store_menu_option
	ON
		store_menu_option.store_menu_id = store_menu.id
WHERE
	store_menu_category.store_id = ?
ORDER BY
	store_menu_category.priority DESC,
	store_menu.priority DESC,
	store_menu_option.priority DESC
