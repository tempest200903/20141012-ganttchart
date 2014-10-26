package com.github.tempest200903.ganttchart.entity;

import java.util.Date;

/**
 * タスク制約の種類。
 * 
 * @author tempest200903
 *
 */
public enum TaskConstraintType {

	/**
	 * できるだけ早く
	 */
	AS_SOON_AS_POSSIBLE {
		@Override
		Date getStartDate(TaskEntity taskEntity) {
			// TODO Auto-generated method stub に反映する
			return taskEntity.getProjectStartDate();
		}

		@Override
		Date getFinishDate(TaskEntity taskEntity) {
			return addDurationToStartDate(taskEntity);
		}

	},

	/**
	 * できるだけ遅く
	 */
	AS_LATE_AS_POSSIBLE {
		@Override
		Date getStartDate(TaskEntity taskEntity) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		Date getFinishDate(TaskEntity taskEntity) {
			// TODO Auto-generated method stub
			return null;
		}
	},

	/**
	 * 指定日に開始
	 */
	MUST_START_ON {
		@Override
		Date getStartDate(TaskEntity taskEntity) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		Date getFinishDate(TaskEntity taskEntity) {
			// TODO Auto-generated method stub
			return null;
		}
	},

	/**
	 * 指定日に終了
	 */
	MUST_FINISH_ON {
		@Override
		Date getStartDate(TaskEntity taskEntity) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		Date getFinishDate(TaskEntity taskEntity) {
			// TODO Auto-generated method stub
			return null;
		}
	},

	/**
	 * 指定日以降に開始
	 */
	START_NO_EARLIER_THAN {
		@Override
		Date getStartDate(TaskEntity taskEntity) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		Date getFinishDate(TaskEntity taskEntity) {
			// TODO Auto-generated method stub
			return null;
		}
	},

	/**
	 * 指定日までに終了
	 */
	START_NO_LATER_THAN {
		@Override
		Date getStartDate(TaskEntity taskEntity) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		Date getFinishDate(TaskEntity taskEntity) {
			// TODO Auto-generated method stub
			return null;
		}
	},

	/**
	 * 指定日以降に終了
	 */
	FINISH_NO_EARLIER_THAN {
		@Override
		Date getStartDate(TaskEntity taskEntity) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		Date getFinishDate(TaskEntity taskEntity) {
			// TODO Auto-generated method stub
			return null;
		}
	},

	/**
	 * 指定日までに開始
	 */
	FINISH_NO_LATER_THAN {
		@Override
		Date getStartDate(TaskEntity taskEntity) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		Date getFinishDate(TaskEntity taskEntity) {
			// TODO Auto-generated method stub
			return null;
		}
	};

	abstract Date getStartDate(TaskEntity taskEntity);

	abstract Date getFinishDate(TaskEntity taskEntity);

	Date addDurationToStartDate(TaskEntity taskEntity) {
		Date startDate = taskEntity.getStartDate();
		long duration = taskEntity.getDuration();
		return new Date(startDate.getTime() + duration);
	}
}
