/*
 * Author
 * Jose Angel
 */

    
    function initDND() {

        $('.ui-icon-arrow-4').draggable({
            helper: 'clone',
            scope: 'treetotable',
            zIndex: ++PrimeFaces.zindex
        });

        $('.ui-datatable .droppoint').droppable({
            activeClass: 'ui-state-active',
            hoverClass: 'ui-state-highlight',
            tolerance: 'pointer',
            scope: 'treetotable',
            drop: function(event, ui) {
                index_tr = $(ui.draggable).closest("tr")[0].rowIndex - 1,
                        worker = $(this).attr('id'),
                        worker_id = $('.workers_list').find("tbody").find("tr").eq(worker.split(":")[2]).find("td").eq(0).html(),
                        sub_activity_id = $('.tableIterationDistribution').find("tbody").find("tr").eq(index_tr).find("td").eq(0).html();

                treeToTable([
                    {name: 'sub_activity_id', value: sub_activity_id}
                    , {name: 'worker_id', value: worker_id}
                ]);
            }
        });
    }

    $(function() {
        initDND();
    });

    function Init() {
        initDND();
        PF('unitDialog').show();
    }
    function WorkDialog() {
        PF('workDialog').show();
    }
    function EditDialog() {
        PF('editDialog').show();
    }